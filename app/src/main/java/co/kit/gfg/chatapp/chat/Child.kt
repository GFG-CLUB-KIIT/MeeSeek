package co.kit.gfg.chatapp.chat

data class Child(var childs: List<Message>) {
    override fun toString(): String {
        return "Child(childs=$childs)"
    }
}