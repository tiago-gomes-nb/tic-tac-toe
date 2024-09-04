(ns tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.core :refer :all]
            [tic-tac-toe.domain.logic :as logic]))

(deftest process-move!-test
  (testing "Valid move"
    (let [board [["-" "-" "-"]
                 ["-" "-" "-"]
                 ["-" "-" "-"]]
          player "X"
          move [0 0]
          expected {:board [["X" "-" "-"]
                            ["-" "-" "-"]
                            ["-" "-" "-"]]
                    :player "O"}]
      (with-redefs [logic/validate-move? (constantly true)
                    logic/fill-board (fn [_ _ _] [["X" "-" "-"]
                                                  ["-" "-" "-"]
                                                  ["-" "-" "-"]])
                    logic/game-over? (constantly false)
                    logic/full-board? (constantly false)
                    logic/switch-player (constantly "O")]
        (is (= expected (process-move! board player move))))))

  (testing "Invalid move"
    (let [board [["X" "-" "-"]
                 ["-" "-" "-"]
                 ["-" "-" "-"]]
          player "O"
          move [0 0]
          expected {:board [["X" "-" "-"]
                            ["-" "-" "-"]
                            ["-" "-" "-"]]
                    :player "O"
                    :message "Invalid move! Try again."}]
      (with-redefs [logic/validate-move? (constantly false)]
        (is (= expected (process-move! board player move))))))

  (testing "Game over"
    (let [board [["X" "X" "-"]
                 ["O" "O" "-"]
                 ["-" "-" "-"]]
          player "X"
          move [0 2]
          expected {:board [["X" "X" "X"]
                            ["O" "O" "-"]
                            ["-" "-" "-"]]
                    :message "Player X wins! Game over!"}]
      (with-redefs [logic/validate-move? (constantly true)
                    logic/fill-board (fn [_ _ _] [["X" "X" "X"]
                                                  ["O" "O" "-"]
                                                  ["-" "-" "-"]])
                    logic/game-over? (constantly true)]
        (is (= expected (process-move! board player move))))))

  (testing "Full board"
    (let [board [["X" "O" "X"]
                 ["X" "O" "O"]
                 ["O" "X" "-"]]
          player "X"
          move [2 2]
          expected {:board [["X" "O" "X"]
                            ["X" "O" "O"]
                            ["O" "X" "X"]]
                    :message "It's a tie! Game over!"}]
      (with-redefs [logic/validate-move? (constantly true)
                    logic/fill-board (fn [_ _ _] [["X" "O" "X"]
                                                  ["X" "O" "O"]
                                                  ["O" "X" "X"]])
                    logic/game-over? (constantly false)
                    logic/full-board? (constantly true)]
        (is (= expected (process-move! board player move)))))))