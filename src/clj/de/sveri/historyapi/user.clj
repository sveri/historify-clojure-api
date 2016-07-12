(ns de.sveri.historyapi.user
  (:require [reloaded.repl :refer [go reset stop]]
          [de.sveri.historyapi.components.components :refer [dev-system]]
          [schema.core :as s]))

(defn start-dev-system []
  (s/set-fn-validation! true)
  (go))

(reloaded.repl/set-init! dev-system)
