(ns nolinks.core
  (:gen-class)
  (require [clojure-csv.core :as csv])
  (require [clojure.java.io :as io]))

  (defn take-csv
    "Takes file name and reads data."
    [fname]
    (with-open [file (io/reader fname)]
      (csv/parse-csv (slurp file) :delimiter \tab)))

  (defn get-pair
    "Returns all gene-drug pairs."
    [genes-set drugs-set]
    (str (nth genes-set 1) "-" (nth drugs-set 1))
    )

(defn -main
  [& args]
  (def drugs-set (list nil))
  (def genes-set (list nil))
  (doseq [keyval (take-csv "drugs.tsv")] (def drugs-set (conj drugs-set (get keyval 0)))
  )
  (doseq [keyval (take-csv "genes.tsv")] (def genes-set (conj genes-set (get keyval 0)))
  )
  (get-pair genes-set drugs-set)

  )
