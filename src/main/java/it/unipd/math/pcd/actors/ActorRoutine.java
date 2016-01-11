/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 Giacomo Cusinato
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * <p/>
 * Please, insert description here.
 *
 * @author Giacomo Cusinato
 * @version 1.0
 * @since 1.0
 */

package it.unipd.math.pcd.actors;


/**
 * A thread the constantly checks an actor mailbox status.
 *
 * @author Giacomo Cusinato
 * @version 1.0
 * @since 1.0
 */
public class ActorRoutine<T extends Message> implements Runnable {

    private final AbsActor actorInstance;
    private final MailBox mailBoxInstance;


    /**
     * This service is responsible to process the received
     * message in a different thread.
     */
    private class ReceiveService extends Thread {

        Message message;

        ReceiveService(Message m) {
            message = m;
        }

        @Override
        public void run() {
            actorInstance.receive(message);

            synchronized (mailBoxInstance) {
                actorInstance.isBusy = false;
                mailBoxInstance.notify();
            }
        }

    }

    public ActorRoutine(AbsActor<? extends Message> actor, MailBox<? extends Message> mailBox) {
        actorInstance = actor;
        mailBoxInstance = mailBox;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.mailBoxInstance) {
                while(mailBoxInstance.size() == 0 || actorInstance.isBusy) {
                    try {
                        mailBoxInstance.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                new ReceiveService(mailBoxInstance.remove(0)).start();
                this.actorInstance.isBusy = true;
            }
        }
    }
}
