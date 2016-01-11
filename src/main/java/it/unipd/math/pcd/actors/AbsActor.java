/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 Riccardo Cardin
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
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */

/**
 * Please, insert description here.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */
package it.unipd.math.pcd.actors;

/**
 * Defines common properties of all actors.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */
public abstract class AbsActor<T extends Message> implements Actor<T> {

    /**
     * Mailbox of the current actor.
     */
    private final MailBox<T> mailBox = new BaseMailBox<>();

    /**
     * Actor's helper thread that checks the incoming messages.
     */
    private final Thread routine;

    /**
     * Tells if the actor is currently processing a received message.
     */
    public boolean isBusy = false;

    /**
     * Self-reference of the actor.
     */
    protected ActorRef<T> self;

    /**
     * Sender of the current message.
     */
    protected ActorRef<T> sender;

    /**
     * Class constructor.
     */
    public AbsActor() {
        routine = new Thread(new ActorRoutine<T>(this, mailBox));
        routine.start();
    }

    /**
     * Sets the self-reference.
     *
     * @param self The reference to itself
     * @return The actor.
     */
    protected final Actor<T> setSelf(ActorRef<T> self) {
        this.self = self;
        return this;
    }

    /**
     * Adds a new message to the actor's mailbox.
     */
    public final void scheduleMessage(T message, ActorRef<T> send) {
        synchronized (this.mailBox) {
            this.sender = send;

            this.mailBox.add(message);
            this.mailBox.notify();
        }
    }

    /**
     * Interrupts the actor's routine.
     */
    public final void interruptRoutine() {
        routine.interrupt();
    }
}
