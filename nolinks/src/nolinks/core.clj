(ns nolinks.core
  (:gen-class)
  (require [clojure-csv.core :as csv])
  (require [clojure.java.io :as io]))

  (defn take-csv
    "Takes file name and reads data."
    [fname]
    (with-open [file (io/reader fname)]
      (csv/parse-csv (slurp file) :delimiter \tab)))


(defn -main
  [& args]
  (def drugs-set (set nil))
  (def genes-set (set nil))
  (doseq [keyval (take-csv "drugs.tsv")] (def drugs-set (conj drugs-set (get keyval 0)))
  )
  (doseq [keyval (take-csv "genes.tsv")] (def genes-set (conj genes-set (get keyval 0)))
  )
  (prn drugs-set)
  (prn genes-set)
  )
