package calculator
import camp.nextstep.edu.missionutils.Console as camp

// Model
class Calculator {
    constructor(inputExp: String) {
        this.inputExp = inputExp
    }
    var inputExp: String = ""
    val sepList = mutableListOf(",", ":") // 구분자 리스트

    // 리스트의 값을 더하는 함수
    fun add(numList: List<String>): Int {
        return if (numList.isEmpty()) 0 else numList.map{ it.toInt() }.sumOf{ it }
    }

    // 커스텀 구분자를 구분자 목록에 추가하고 문자열을 리스트로 반환하는 함수
    fun findSeparatorAndSplit(): List<String> {
        var newExp = inputExp
        if (!inputExp.isBlank()) {
            val s = inputExp.substring(0, 2) // 입력 문자열 시작에서 //를 찾기 위함
            val e = inputExp.indexOf("\\n") // 입력 문자열에서 \n의 인덱스
            if (s == "//" && inputExp.contains("\\n")) {
                sepList.add(inputExp.substring(2, e))
                newExp = inputExp.substring(e + 2, inputExp.length)
            }
        }
        return newExp.split(Regex("[" + sepList.joinToString("") + "]")).filter{ it != "" }
    }

    // 예외 처리 함수
    fun exceptionCheck(numList: List<String>) {
        if (numList.any{ it.toIntOrNull() == null || it.toInt() < 0 }) throw IllegalArgumentException("양의 정수가 아닌 문자를 입력하였습니다.")
    }
}

// View
class View {
    companion object {
        // 시작 함수
        fun start() {
            println("덧셈할 문자열을 입력해 주세요.")
        }

        // 결과 반환 함수
        fun end(result: Int) {
            println("결과 : $result")
        }
    }

}

// Controller
object Controller {
    fun run() {
        View.start()
        val inputExp: String = camp.readLine() // 사용자 입력
        val cal = Calculator(inputExp)

        val numList = cal.findSeparatorAndSplit()
        cal.exceptionCheck(numList)

        val result = cal.add(numList)
        View.end(result)
    }
}

fun main() {
    // TODO: 프로그램 구현
    Controller.run()
}
