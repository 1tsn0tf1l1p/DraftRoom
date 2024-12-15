package raf.draft.dsw.model.prototype;

/**
 * The `Prototype` interface defines a contract for creating copies of objects
 * using the Prototype design pattern. Classes implementing this interface must
 * provide a `clone` method to produce a duplicate of the object.
 *
 * <p>
 * This interface is particularly useful when the cost of creating a new object
 * is high, and cloning an existing object is more efficient. It also enables
 * dynamic and flexible object creation, especially when the exact type of the
 * object to be created is determined at runtime.
 * </p>
 */
public interface Prototype {

    /**
     * Creates and returns a copy (clone) of the current object.
     * The specific implementation determines whether the clone is a shallow copy
     * or a deep copy.
     *
     * @return a new instance of the object, cloned from the current instance.
     */
    Prototype clone();
}
