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
 * An abstract implementation of the Actor reference.
 *
 * @author Giacomo Cusinato
 * @version 1.0
 * @since 1.0
 */
public abstract class AbsActorRef<T extends Message> implements ActorRef<T> {

    protected final AbsActorSystem actorSystem;

    /**
     * AbsActorRef constructor. Requires an instance of the ActorSystem.
     *
     * @param system the instance of the BaseActorSystem object
     */
    public AbsActorRef(AbsActorSystem system) {
        actorSystem = system;
    }

    /**
     * Check if an instance of an ActorRef is the same of the current object instance.
     *
     * @param o ActorRef compared to the current instance
     * @return 0 if the instance is the same, -1 otherwise
     */
    @Override
    public int compareTo(ActorRef o) {
        return (this == o) ? 0 : -1;
    }
}
