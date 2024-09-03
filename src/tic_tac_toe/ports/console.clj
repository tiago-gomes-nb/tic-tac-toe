(ns tic-tac-toe.ports.console)

(defn get-entry! []
  (println "Enter your move (line and column, separated by space):")
  (let [entry (read-line)]
    (mapv #(Integer/parseInt %) (clojure.string/split entry #" "))))

(defn print-board!
  [board]
  (doseq [row board]
    (doseq [cell row]
      (print cell))
    (println)))

(defn print-result!
  [result]
  (println "----------------")
  (print-board! (:board result))
  (when (:message result)
    (println (:message result))))