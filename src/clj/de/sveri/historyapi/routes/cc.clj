(ns de.sveri.historyapi.routes.cc
  (:require [compojure.core :refer [routes GET POST]]
            [schema.core :as s]
            [ring.util.response :refer [response status]]
            [taoensso.timbre :as timb]
            [clojure.pprint :as pp]
            [de.sveri.historyapi.layout :as layout]
            [de.sveri.historyapi.closp-schema :as c-schem])
  (:import (java.io File)
           (java.util UUID)))

(defn cc-page []
  (layout/render "cc/index.html"))

(s/defn initial-data :- (c-schem/wrap-with-response {:ex-entities c-schem/cc-entity-definitons})
  [config :- s/Any]
  (let [files (->> (:closp-definitions config)
                   (File.)
                   (file-seq)
                   (filter #(.isFile %)))
        entities (mapv #(read-string (slurp %)) files)]
    (response {:ex-entities entities})))

(s/defn write-cc-entity-to-file :- s/Any
  [new-entity :- c-schem/cc-entity-definiton config :- s/Any]
  (spit
    (File. (str (:closp-definitions config) "/"
                (get-in new-entity [:name] (.toString (UUID/randomUUID))) ".edn"))
    (with-out-str (pp/pprint new-entity))))

(s/defn add-new-entity :-
  (c-schem/wrap-with-response {:ok s/Str :added-entity c-schem/cc-entity-definiton})
  [new-entity :- c-schem/cc-entity-definiton config :- s/Any]
  (try
    (write-cc-entity-to-file new-entity config)
    (response {:ok "fine" :added-entity new-entity})
    (catch Exception e
      (do (timb/error e)
          (status 500 (response {:error "Something failed while saving the entity"}))))))

(defn cc-routes [config]
  (routes
    (GET "/admin/cc" [] (cc-page))
    (GET "/admin/cc/entities" [] (initial-data config))
    (POST "/admin/cc/entities" req (add-new-entity (:params req) config))))
