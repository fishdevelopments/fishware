package de.fishdevelopments.fishware.manager

abstract class ClassInstanceManager<T : Any> {
  val instances: MutableMap<Class<out T>, T> = HashMap()

  abstract fun run()

  fun register(clazz: Class<out T>) {
    this.instances[clazz] = clazz.getConstructor().newInstance()
  }

  fun register(instance: T) {
    this.instances[instance::class.java] = instance
  }

  fun register(clazz: Class<out T>, instance: T) {
    this.instances[clazz] = instance
  }

  fun <U : T> get(clazz: Class<U>): U {
    @Suppress("UNCHECKED_CAST")
    return this.instances[clazz] as U
  }

  fun getBy(predicate: (T) -> Boolean): List<T> {
    return this.instances.values.filter(predicate)
  }

  fun getValues(): Collection<T> {
    return this.instances.values
  }
}
