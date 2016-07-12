(ns historyapi.dev
  (:require [schema.core :as s]
            [de.sveri.historyapi.core :as core]))

(s/set-fn-validation! true)

(enable-console-print!)

(defn main [] (core/main))
