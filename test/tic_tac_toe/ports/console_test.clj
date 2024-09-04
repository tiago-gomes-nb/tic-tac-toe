(ns tic-tac-toe.ports.console-test
  (:require [clojure.test :refer :all])
  (:require [tic-tac-toe.ports.console :refer :all]))

(deftest get-entry!-test
  (testing "Should parse user input correctly"
    (with-in-str "1 2\n"
      (is (= [1 2] (get-entry!))))))

(deftest print-board!-test
  (testing "Should print the board correctly"
    (let [board [["-" "-" "-"]
                 ["-" "X" "-"]
                 ["O" "-" "-"]]]
      (with-out-str
        (print-board! board)
        (is (= "---\n-X-\nO--\n" (with-out-str (print-board! board))))))))

(deftest print-result!-test
  (testing "Should print the result correctly"
    (let [result {:board [["-" "-" "-"]
                          ["-" "X" "-"]
                          ["O" "-" "-"]]
                  :message "Player X wins!"}]
      (with-out-str
        (print-result! result)
        (is (= "----------------\n---\n-X-\nO--\nPlayer X wins!\n" (with-out-str (print-result! result))))))))
