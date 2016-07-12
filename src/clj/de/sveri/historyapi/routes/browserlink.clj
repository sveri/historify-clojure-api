(ns de.sveri.historyapi.routes.browserlink
  (:require [compojure.core :refer [routes GET POST]]
            [ring.util.response :refer [response status]]
            [de.sveri.historyapi.db.browser_link :as db]
            [clojure.java.jdbc :as j]
            [hugsql.core :as hugsql]))


(def db-spec {:connection-uri "jdbc:postgresql://localhost/historify?user=postgres&password=postgres"})


(defn browserlink []
  (response (j/query db-spec
                     ["select * from browser_link limit 5"])))


(defn browserlink-routes [config]
  (routes
    (GET "/api/browser_link" [] (browserlink))))

