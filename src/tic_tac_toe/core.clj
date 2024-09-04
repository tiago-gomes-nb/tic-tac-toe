(ns tic-tac-toe.core)
(require '[tic-tac-toe.domain.logic :as logic])
(require '[tic-tac-toe.ports.console :as console])

(defn process-move!
  [board player move]
  (if (logic/validate-move? board move)
    (let [new-board (logic/fill-board board move player)]
      (cond
        (logic/game-over? new-board player) {:board new-board :message (str "Player " player " wins! Game over!")}
        (logic/full-board? new-board) {:board new-board :message "It's a tie! Game over!"}
        :else {:board new-board :player (logic/switch-player player)}))
    {:board board :player player :message "Invalid move! Try again."}))

(defn play!
  []
  (let [initial-board (->> (repeat 3 "-")
                          (vec)
                          (repeat 3)
                          (vec))
        initial-player "X"]
    (console/print-board! initial-board)
    (loop [board initial-board player initial-player]
      (let [move (console/get-entry!)
            result (process-move! board player move)]
        (console/print-result! result)
        (when (and (:board result) (:player result))
          (recur (:board result) (:player result)))))))

(defn -main []
  (play!))