(ns tic-tac-toe.test-utils)

(defn generate-mock-board
  [positions]
  (let [initial-board (->> (repeat 3 "-")
                           (vec)
                           (repeat 3)
                           (vec))]
    (reduce (fn [board [[row col] value]]
              (assoc-in board [row col] value))
            initial-board
            positions)))