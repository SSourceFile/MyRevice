package com.fire.myreivces.ui.designMod.builder

class MyBuilder private constructor(builder: Builder) {

    internal var name: String? = null
    init {
        name = builder.name
    }

    class Builder{
        internal var name: String? = null
        fun setMyName(name: String):Builder{
            this.name = name
            return this
        }
        fun build(): MyBuilder{
            return MyBuilder(this)
        }
    }
}