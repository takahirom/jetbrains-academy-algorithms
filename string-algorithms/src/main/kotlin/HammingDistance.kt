class HammingDistance {
    fun sample() {
        val a = "microscope"
        val b = "microphone"
        println(distance(a, b))
    }

    fun main5244(){
        println(distance("antelope", "envelope"))
    }

    private fun distance(a: String, b: String): Int {
        return a.withIndex().count { (index, c) ->
            b[index] != c
        }
    }

}

fun main() {
    HammingDistance().main5244()
}