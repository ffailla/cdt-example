(ns ffailla.cdtexample
  (:use compojure.core, ring.adapter.jetty)
  (:require [compojure.route :as route]))

(defn make-map [arg]
  (apply array-map (range (Integer. arg))))

(defn test-fn [arg1 arg2]
  (let [a (str arg1 " abcdefg")
	b (make-map arg2)]
    [a b]))

(defroutes main-routes
  (GET "/" {params :params}
       (let [arg1 (or (params "arg1") "test string arg")
	     arg2 (or (params "arg2") 10)]
	 (apply str (interpose "\n" (test-fn arg1 arg2)))))
  (route/not-found "<h1>Page not found</h1>"))

(defn start []
  (run-jetty main-routes {:port 8081 :join? false}))