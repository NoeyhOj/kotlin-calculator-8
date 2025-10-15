package calculator

class Calculator(var userInput: String) {

    var sepList = mutableListOf(",", ":") // 구분자 리스트

    fun printComponent() {
        println(userInput)
        println(sepList)
    }

    // 구분자 찾아내는 함수
    fun findSeparator() {
        if (userInput.substring(0, 2) == "//" && userInput.substring(3, 5) == "\\n") {
            sepList.add(userInput[2].toString())
            userInput = userInput.substring(5)
        }
    }

    // 연산 후 결과를 출력하는 함수
    fun printResult() {
        val numList = userInput.split(Regex("[" + sepList.joinToString("") + "]")).filter{ it != "" }
        require(numList.all{ it.contains(Regex("[0-9]"))}) { "정수가 아닌 다른 문자가 입력되었습니다."}
        val result = numList.map{ it.toInt() }.sumOf{ it }
        println("결과 : $result")
    }
}

fun main() {
    // TODO: 프로그램 구현
    println("덧셈할 문자열을 입력해 주세요.") // 시작 문구
    val userInput: String = readLine()?:"" // 사용자 입력

    if (userInput.isEmpty()) {
        println("0")
    }  else {
        val cal = Calculator(userInput)

        cal.printComponent()
        cal.findSeparator()

        cal.printComponent()
        cal.printResult()
    }
}
