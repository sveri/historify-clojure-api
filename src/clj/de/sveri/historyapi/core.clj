(ns de.sveri.historyapi.core
  (:require [taoensso.timbre :as timbre]
            [reloaded.repl :refer [go]]
            [de.sveri.historyapi.cljccore :as cljc]
            [de.sveri.historyapi.components.components :refer [prod-system]])
  (:gen-class))

(defn -main [& args]
  (reloaded.repl/set-init! prod-system)
  (go)
  (cljc/foo-cljc "hello from cljx")
  (timbre/info "server started."))
