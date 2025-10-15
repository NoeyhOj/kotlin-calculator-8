package calculator

class SeparatorCalculator(var userInput: String) {

    var sepList = mutableListOf(",", ":") // 구분자 리스트
    var numList: List<String> = listOf()

    // 구분자 찾아내는 함수
    fun findSeparator() {
        if (userInput != "") {
            val s = userInput.substring(0, 2) // 입력 문자열 시작에서 //를 찾기 위함
            val e = userInput.indexOf("\\n") // 입력 문자열에서 \n의 인덱스
            if (s == "//" && userInput.contains("\\n")) {
                sepList.add(userInput.substring(2, e))
                userInput = userInput.substring(e + 2, userInput.length)
            }
        }
        numList = userInput.split(Regex("[" + sepList.joinToString("") + "]")).filter{ it != "" }
    }

    // 예외처리 함수
    fun exceptionCheck() {
        if (numList.any{ it.toIntOrNull() == null || it.toInt() < 0 }) throw IllegalArgumentException("양의 정수가 아닌 문자를 입력하였습니다.")
    }

    // 연산 후 결과 출력 함수
    fun printResult() {
        val result = if (numList.isEmpty()) 0 else numList.map{ it.toInt() }.sumOf{ it }
        println("결과 : $result")
    }
}

fun main() {
    // TODO: 프로그램 구현
    println("덧셈할 문자열을 입력해 주세요.") // 시작 문구
    val userInput: String = readLine()?:"" // 사용자 입력

    val sc = SeparatorCalculator(userInput)
    sc.findSeparator()
    sc.exceptionCheck()
    sc.printResult()
}
