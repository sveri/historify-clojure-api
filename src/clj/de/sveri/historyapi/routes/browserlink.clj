(ns de.sveri.historyapi.routes.browserlink
  (:require [compojure.core :refer [routes GET POST]]
            [ring.util.response :refer [response status]]
            [de.sveri.historyapi.db.browser_link :as db]))



(defn browserlink []
  (response (db/get-all-5)))

(defn browserlink-routes [config]
  (routes
    (GET "/api/browser_link" [] (browserlink))))

