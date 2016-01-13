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


import it.unipd.math.pcd.actors.exceptions.NoSuchActorException;

/**
 * A concrete implementation of the map-based actor system.
 *
 * @author Giacomo Cusinato
 * @version 1.0
 * @since 1.0
 */
public class BaseActorSystem extends AbsActorSystem {

    /**
     * Creates an ActorRef object of the given type.
     *
     * @param mode type of the ActorRef to be created
     * @return the created ActorRef object
     */
    @Override
    protected ActorRef createActorReference(ActorMode mode) {

        if (mode == ActorMode.LOCAL) {
            return new LocalActorRef(this);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void stop() {
        for (ActorRef<?> actor : this.actors.keySet()) {
            this.stop(actor);
        }
    }

    @Override
    public void stop(ActorRef<?> actor) throws NoSuchActorException {
        if (actors.containsKey(actor)) {
            actors.remove(actor);
        } else {
            throw new NoSuchActorException();
        }
    }
}
