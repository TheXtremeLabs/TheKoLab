package fr.lvmvrquxl.thekolab.colors.model.color

/**
 * Color's abstract representation.
 *
 * @param name Color's name
 * @param value Color's value
 */
internal abstract class Color(val name: String?, val value: Int?) {
    override fun equals(other: Any?): Boolean =
        other is Color && this.name == other.name && this.value == other.value

    override fun hashCode(): Int = this.name.hashCode() + this.value.hashCode()
}
