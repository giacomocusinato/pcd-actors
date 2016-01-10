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

/**
 * Please, insert description here.
 *
 * @author Giacomo Cusinato
 * @version 1.0
 * @since 1.0
 */
package it.unipd.math.pcd.actors;

/**
 * Defines the interface of the mailbox, a container that manages the incoming
 * messages of a specific actor.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */
public interface MailBox<T extends Message> {

    /**
     * Adds a message at the end of the mailbox.
     *
     * @param message The type of messages the actor can receive
     */
    boolean add(T message);

    /**
     * Adds a message at a defined index in the mailbox.
     *
     * @param message The type of messages the actor can receive
     * @param index The index of the list to add the message to.
     */
    void add(int index, T message);

    /**
     * Removes a message from the mailbox.
     *
     * @param message The type of messages the actor can receive
     */
    T remove(int index);

    /**
     * Gets the size of the mailbox.
     *
     * @return The number of messages stored in the mailbox
     */
    int size();
}
