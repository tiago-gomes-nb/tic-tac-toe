(ns tic-tac-toe.domain.logic)

(defn validate-entry?
  [move]
  (->> move
       (every? #(and (>= % 0) (< % 3)))))

(defn allowedPosition?
  [board move]
  (let [[line column] move]
    (= (get-in board [line column]) "-")))

(defn validate-move?
  [board move]
  (and (validate-entry? move)
       (allowedPosition? board move)))

(defn fill-board
  [board move player]
  (let [[line column] move]
    (assoc-in board [line column] player)))

(defn switch-player
  [player]
  (if (= player "X") "O" "X"))

(defn check-rows
  [board player row]
  (cond
    (= row 3) false
    (and (= (get-in board [row 0]) player)
         (= (get-in board [row 1]) player)
         (= (get-in board [row 2]) player)) true
    :else (recur board player (inc row))))

(defn check-columns
  [board player col]
  (cond
    (= col 3) false
    (and (= (get-in board [0 col]) player)
         (= (get-in board [1 col]) player)
         (= (get-in board [2 col]) player)) true
    :else (recur board player (inc col))))

(defn check-diagonals
  [board player]
  (or (and (= (get-in board [0 0]) player)
           (= (get-in board [1 1]) player)
           (= (get-in board [2 2]) player))
      (and (= (get-in board [0 2]) player)
           (= (get-in board [1 1]) player)
           (= (get-in board [2 0]) player))))

(defn game-over?
  [board player]
  (or (check-rows board player 0)
      (check-columns board player 0)
      (check-diagonals board player)))

(defn tie? [board]
  (not-any? #(= "-" %) (flatten board)))