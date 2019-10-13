(ns brave-clojure.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn map-set-loop
  "Variation of 'map' that returns a set. Uses 'loop' for evaluation."
  [f collection]
  (loop [c collection
         mapped-set #{}]
    (if (empty? c)
      mapped-set
      (recur (rest c) (into mapped-set [(f (first c))])))))

(defn map-set
  "Variation of 'map' that returns a set. Uses 'reduce' for evaluation."
  [func collection]
  (reduce (fn
            [coll item]
            (into coll (set [(func item)])))
          #{}
          collection))

(defn map-reduce
  "Implementation of 'map' using reduce"
  [func collection]
  (reduce (fn [coll item]
            (into coll (set [(func item)])))
          '()
          collection))

(defn filter-reduce
  "Implementation of 'filter' using reduce."
  [func collection]
  (reduce (fn [coll item]
            (if (func item)
              (concat coll (list item))
              coll))
          ()
          collection))

(defn some-reduce
  "Implementation of 'some' using reduce."
  [func collection]
  (reduce (fn [result item]
            (or (or result (func item)) nil))
          nil
          collection))
