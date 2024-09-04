(ns tic-tac-toe.domain.logic-test
  (:require [clojure.test :refer :all])
  (:require [tic-tac-toe.domain.logic :refer :all])
  (:require [tic-tac-toe.test-utils :refer :all]))

(deftest validate-entry?-test

  (testing "Should return true if user entry is valid"
    (is (validate-entry? [0 0]))
    (is (validate-entry? [2 2]))
    (is (validate-entry? [1 1]))
    (is (validate-entry? [0 2]))
    (is (validate-entry? [2 0]))
    (is (validate-entry? [1 0]))
    (is (validate-entry? [1 2]))
    (is (validate-entry? [2 1]))
    (is (validate-entry? [0 1])))

  (testing "Should return false if user entry is invalid"
    (is (not (validate-entry? [0 3])))
    (is (not (validate-entry? [3 0])))
    (is (not (validate-entry? [3 3])))
    (is (not (validate-entry? [0 -1])))
    (is (not (validate-entry? [-1 0])))
    (is (not (validate-entry? [-1 -1])))
    (is (not (validate-entry? [0 0 0])))
    (is (not (validate-entry? [nil nil])))
    (is (not (validate-entry? [nil])))
    (is (not (validate-entry? [0]))))

  (testing "Should return false if user entry is not a vector"
    (is (not (validate-entry? 0)))
    (is (not (validate-entry? "A")))
    (is (not (validate-entry? "0")))
    (is (not (validate-entry? nil)))))

(deftest allowedPosition?-test

  (testing "Should return true if position is allowed"
    (let [mocked-board
          (generate-mock-board [])]
      (is (allowedPosition? mocked-board [0 0]))
      (is (allowedPosition? mocked-board [0 1]))
      (is (allowedPosition? mocked-board [0 2]))
      (is (allowedPosition? mocked-board [1 0]))
      (is (allowedPosition? mocked-board [1 1]))
      (is (allowedPosition? mocked-board [1 2]))
      (is (allowedPosition? mocked-board [2 0]))
      (is (allowedPosition? mocked-board [2 1]))
      (is (allowedPosition? mocked-board [2 2]))))

  (testing "Should return false if position is not allowed"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "O"] [[0 2] "X"]
                                [[1 0] "O"] [[1 1] "X"] [[1 2] "O"]
                                [[2 0] "X"] [[2 1] "O"] [[2 2] "X"]])]
      (is (not (allowedPosition? mocked-board [0 0])))
      (is (not (allowedPosition? mocked-board [0 1])))
      (is (not (allowedPosition? mocked-board [0 2])))
      (is (not (allowedPosition? mocked-board [1 0])))
      (is (not (allowedPosition? mocked-board [1 1])))
      (is (not (allowedPosition? mocked-board [1 2])))
      (is (not (allowedPosition? mocked-board [2 0])))
      (is (not (allowedPosition? mocked-board [2 1])))
      (is (not (allowedPosition? mocked-board [2 2])))))

  (testing "Should return false if position is not allowed and return true if position is allowed"
    (let [mocked-board
          (generate-mock-board [
                                [[0 1] "O"] [[0 2] "X"]
                                [[1 0] "O"] [[1 2] "O"]
                                [[2 0] "X"] [[2 1] "O"]])]
      (is (allowedPosition? mocked-board [0 0]))
      (is (not (allowedPosition? mocked-board [0 1])))
      (is (not (allowedPosition? mocked-board [0 2])))
      (is (not (allowedPosition? mocked-board [1 0])))
      (is (allowedPosition? mocked-board [1 1]))
      (is (not (allowedPosition? mocked-board [1 2])))
      (is (not (allowedPosition? mocked-board [2 0])))
      (is (not (allowedPosition? mocked-board [2 1])))
      (is (allowedPosition? mocked-board [2 2])))))

(deftest validate-move?-test

  (testing "Should return true if move is valid"
    (let [mocked-board
          (generate-mock-board [])]
      (is (validate-move? mocked-board [0 0]))
      (is (validate-move? mocked-board [0 1]))
      (is (validate-move? mocked-board [0 2]))
      (is (validate-move? mocked-board [1 0]))
      (is (validate-move? mocked-board [1 1]))
      (is (validate-move? mocked-board [1 2]))
      (is (validate-move? mocked-board [2 0]))
      (is (validate-move? mocked-board [2 1]))
      (is (validate-move? mocked-board [2 2]))))

  (testing "Should return false if move is invalid"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "O"] [[0 2] "X"]
                                [[1 0] "O"] [[1 1] "X"] [[1 2] "O"]
                                [[2 0] "X"] [[2 1] "O"] [[2 2] "X"]])]
      (is (not (validate-move? mocked-board [0 0])))
      (is (not (validate-move? mocked-board [0 1])))
      (is (not (validate-move? mocked-board [0 2])))
      (is (not (validate-move? mocked-board [1 0])))
      (is (not (validate-move? mocked-board [1 1])))
      (is (not (validate-move? mocked-board [1 2])))
      (is (not (validate-move? mocked-board [2 0])))
      (is (not (validate-move? mocked-board [2 1])))
      (is (not (validate-move? mocked-board [2 2])))
      (is (not (validate-move? mocked-board [0 3])))
      (is (not (validate-move? mocked-board [3 0])))
      (is (not (validate-move? mocked-board [3 3])))
      (is (not (validate-move? mocked-board [0 -1])))
      (is (not (validate-move? mocked-board [-1 0])))
      (is (not (validate-move? mocked-board [-1 -1])))
      (is (not (validate-move? mocked-board [0 0 0])))
      (is (not (validate-move? mocked-board [nil nil])))
      (is (not (validate-move? mocked-board [nil])))
      (is (not (validate-move? mocked-board [0])))))

  (testing "Should return false if move is invalid and return true if move is valid"
    (let [mocked-board
          (generate-mock-board [
                                [[0 1] "O"] [[0 2] "X"]
                                [[1 0] "O"] [[1 2] "O"]
                                [[2 0] "X"] [[2 1] "O"]])]
      (is (validate-move? mocked-board [0 0]))
      (is (not (validate-move? mocked-board [0 1])))
      (is (not (validate-move? mocked-board [0 2])))
      (is (not (validate-move? mocked-board [1 0])))
      (is (validate-move? mocked-board [1 1]))
      (is (not (validate-move? mocked-board [1 2])))
      (is (not (validate-move? mocked-board [2 0])))
      (is (not (validate-move? mocked-board [2 1])))
      (is (validate-move? mocked-board [2 2])))))

(deftest fill-board-test

  (testing "Should fill board with player symbol"
    (let [mocked-board
          (generate-mock-board [])]
      (is (= (fill-board mocked-board [0 0] "X") (generate-mock-board [[[0 0] "X"]])))
      (is (= (fill-board mocked-board [0 1] "O") (generate-mock-board [[[0 1] "O"]])))
      (is (= (fill-board mocked-board [0 2] "X") (generate-mock-board [[[0 2] "X"]])))
      (is (= (fill-board mocked-board [1 0] "O") (generate-mock-board [[[1 0] "O"]])))
      (is (= (fill-board mocked-board [1 1] "X") (generate-mock-board [[[1 1] "X"]])))
      (is (= (fill-board mocked-board [1 2] "O") (generate-mock-board [[[1 2] "O"]])))
      (is (= (fill-board mocked-board [2 0] "X") (generate-mock-board [[[2 0] "X"]])))
      (is (= (fill-board mocked-board [2 1] "O") (generate-mock-board [[[2 1] "O"]])))
      (is (= (fill-board mocked-board [2 2] "X") (generate-mock-board [[[2 2] "X"]])))))

  (testing "Should fill board with player symbol and return the same board if move is invalid"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "O"] [[0 2] "X"]
                                [[1 0] "O"] [[1 1] "X"] [[1 2] "O"]
                                [[2 0] "X"] [[2 1] "O"] [[2 2] "X"]])]
      (is (= (fill-board mocked-board [0 0] "X") mocked-board))
      (is (= (fill-board mocked-board [0 1] "O") mocked-board))
      (is (= (fill-board mocked-board [0 2] "X") mocked-board))
      (is (= (fill-board mocked-board [1 0] "O") mocked-board))
      (is (= (fill-board mocked-board [1 1] "X") mocked-board))
      (is (= (fill-board mocked-board [1 2] "O") mocked-board))
      (is (= (fill-board mocked-board [2 0] "X") mocked-board))
      (is (= (fill-board mocked-board [2 1] "O") mocked-board))
      (is (= (fill-board mocked-board [2 2] "X") mocked-board)))))

(deftest switch-player-test

  (testing "Should switch player"
    (is (= (switch-player "X") "O"))
    (is (= (switch-player "O") "X")))

  (testing "Should return nil if player is nil"
    (is (= (switch-player nil) nil))))

(deftest check-rows-test

  (testing "Should return true if player wins"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "X"] [[0 2] "X"]
                                [[1 0] "O"] [[1 1] "O"] [[1 2] "O"]
                                [[2 0] "O"] [[2 1] "O"] [[2 2] "O"]])]
      (is (check-rows mocked-board "X" 0))
      (is (check-rows mocked-board "O" 1))
      (is (check-rows mocked-board "O" 2))))

  (testing "Should return false if player does not win"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "O"] [[0 2] "X"]
                                [[1 0] "O"] [[1 1] "X"] [[1 2] "O"]
                                [[2 0] "X"] [[2 1] "O"] [[2 2] "X"]])]
      (is (not (check-rows mocked-board "X" 0)))
      (is (not (check-rows mocked-board "O" 1)))
      (is (not (check-rows mocked-board "O" 2))))))

(deftest check-columns-test

  (testing "Should return true if player wins"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "O"] [[0 2] "O"]
                                [[1 0] "X"] [[1 1] "O"] [[1 2] "O"]
                                [[2 0] "X"] [[2 1] "O"] [[2 2] "O"]])]
      (is (check-columns mocked-board "X" 0))
      (is (check-columns mocked-board "O" 1))
      (is (check-columns mocked-board "O" 2))))

  (testing "Should return false if player does not win"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "O"] [[0 2] "X"]
                                [[1 0] "O"] [[1 1] "X"] [[1 2] "O"]
                                [[2 0] "X"] [[2 1] "O"] [[2 2] "X"]])]
      (is (not (check-columns mocked-board "X" 0))
          (is (not (check-columns mocked-board "O" 1))
              (is (not (check-columns mocked-board "O" 2))))))))

(deftest check-diagonals-test

  (testing "Should return true if player wins"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "O"] [[0 2] "O"]
                                [[1 0] "O"] [[1 1] "X"] [[1 2] "O"]
                                [[2 0] "O"] [[2 1] "O"] [[2 2] "X"]])]
      (is (check-diagonals mocked-board "X"))))

  (testing "Should return false if player does not win"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "O"] [[0 2] "X"]
                                [[1 0] "O"] [[1 1] "X"] [[1 2] "O"]
                                [[2 0] "X"] [[2 1] "O"] [[2 2] "X"]])]
      (is (check-diagonals mocked-board "X"))
      (is (not (check-diagonals mocked-board "O"))))))

(deftest game-over?-test

  (testing "Should return true if player wins in lines"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "X"] [[0 2] "X"]
                                [[1 0] "O"] [[1 1] "O"] [[1 2] "O"]
                                [[2 0] "O"] [[2 1] "O"] [[2 2] "O"]])]
      (is (game-over? mocked-board "X"))
      (is (game-over? mocked-board "O"))))

  (testing "Should return true if player wins in columns"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "O"] [[0 1] "X"] [[0 2] "O"]
                                [[1 0] "O"] [[1 1] "X"] [[1 2] "O"]
                                [[2 0] "O"] [[2 1] "X"] [[2 2] "X"]])]
      (is (game-over? mocked-board "X"))
      (is (game-over? mocked-board "O"))))

  (testing "Should return true if player wins in diagonals"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "O"] [[0 2] "O"]
                                [[1 0] "O"] [[1 1] "X"] [[1 2] "O"]
                                [[2 0] "O"] [[2 1] "O"] [[2 2] "X"]])]
      (is (game-over? mocked-board "X"))
      (is (not (game-over? mocked-board "O")))))

  (testing "Should return false if player does not win"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "X"] [[0 2] "O"]
                                [[1 0] "O"] [[1 1] "O"] [[1 2] "X"]
                                [[2 0] "X"] [[2 1] "O"] [[2 2] "X"]])]
      (is (not (game-over? mocked-board "X")))
      (is (not (game-over? mocked-board "O"))))))

(deftest full-board?-test

  (testing "Should return true if board is full"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "O"] [[0 2] "X"]
                                [[1 0] "O"] [[1 1] "X"] [[1 2] "O"]
                                [[2 0] "O"] [[2 1] "X"] [[2 2] "O"]])]
      (is (full-board? mocked-board))))

  (testing "Should return false if board is not full"
    (let [mocked-board
          (generate-mock-board [
                                [[0 0] "X"] [[0 1] "X"] [[0 2] "O"]
                                [[1 0] "-"] [[1 1] "O"] [[1 2] "X"]
                                [[2 0] "X"] [[2 1] "-"] [[2 2] "X"]])]
      (is (not (full-board? mocked-board))))))