package data.gmail

object MessageCache {

    private val cacheMap = HashMap<String, MessageWrapper>()

    fun store(message: MessageWrapper) {
        message.getId()?.let {
            cacheMap.put(it.value, message)
        }
    }

    fun get(id: String): MessageWrapper? {
        return cacheMap[id]
    }
}