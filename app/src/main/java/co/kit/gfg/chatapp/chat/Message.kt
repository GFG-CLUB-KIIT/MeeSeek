package co.kit.gfg.chatapp.chat

interface Message {
    fun content(): Content
    fun father(): Father
    fun child(): Child?
}