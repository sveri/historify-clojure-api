(ns de.sveri.historyapi.db.browser_link
  (:require [korma.core :refer [select where insert delete values update set-fields defentity limit order]]
            [korma.db :refer [h2]]
            [de.sveri.historyapi.db.entities :refer [browser_link]]))

(defn get-all-5 []
  (select browser_link (limit 5)))
