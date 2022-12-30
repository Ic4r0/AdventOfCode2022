package utils

class CloneElements {
    fun cloneMapOfLists(mapOfLists: MutableMap<Int, MutableList<String>>): MutableMap<Int, MutableList<String>> {
        val clonedMap: MutableMap<Int, MutableList<String>> = mutableMapOf()
        mapOfLists.keys.forEach {
            clonedMap[it] = mapOfLists[it]!!.toMutableList()
        }
        return clonedMap
    }
}