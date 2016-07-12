(ns de.sveri.historyapi.closp-schema-helper)

(defmulti get-type-of-column (fn [v] #?(:clj (seq? (second v)) :cljs (seqable? (second v)))))
(defmethod get-type-of-column true [_] :varchar)
(defmethod get-type-of-column :default [v] (second v))


(defn boolean? [value]
  #?(:clj  (instance? Boolean value)
     :cljs (= js/Boolean (type value))))

(defmulti is-correct-default-type (fn [expected-type _] expected-type))
(defmethod is-correct-default-type :varchar [_ type]
  (string? type))
(defmethod is-correct-default-type :text [_ type]
  (is-correct-default-type :varchar type))
(defmethod is-correct-default-type :int [_ type]
  (integer? type))
(defmethod is-correct-default-type :time [_ type]
  (type))
(defmethod is-correct-default-type :boolean [_ type]
  (boolean? type))
(defmethod is-correct-default-type :default [_ type]
  (string? type))


(defn validate-options [v]
  (let [expected-type (get-type-of-column v)]
    (reduce (fn [valid? [key value]]
              (cond
                (contains? #{:unique :null :pk :autoinc} key) (and valid? (boolean? value))
                (= :default key) (and valid? (is-correct-default-type expected-type value))
                :else false))
            true (partition 2 (subvec v 2)))))

(defn table-column-pred [v]
  (cond
    (< (count v) 3) true
    (odd? (count v)) false
    :else (validate-options v)))
