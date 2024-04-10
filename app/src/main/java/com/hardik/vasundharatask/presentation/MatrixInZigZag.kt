package com.hardik.vasundharatask.presentation

fun printMatrixInZigZag(matrix: Array<IntArray>) {
    val n = matrix.size
    val m = matrix[0].size

    var row = 0
    var col = 0
    var goingDown = true

    while (row in 0 until n && col in 0 until m) {
        print(matrix[row][col])
        if (goingDown) {
            if (row == n - 1) {
                col++
                goingDown = false
            } else if (col == 0) {
                row++
                goingDown = false
            } else {
                row++
                col--
            }
        } else {
            if (col == m - 1) {
                row++
                goingDown = true
            } else if (row == 0) {
                col++
                goingDown = true
            } else {
                row--
                col++
            }
        }
    }
}

fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )
    printMatrixInZigZag(matrix)
}
