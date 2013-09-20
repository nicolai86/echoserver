(ns echoserver.core
  (:require clojure.contrib.server-socket))

(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])

(defn echo-server []
  (letfn [(echo [in out]
                    (binding [*in* (BufferedReader. (InputStreamReader. in))
                              *out* (OutputStreamWriter. out)]
                      (loop []
                        (let [input (read-line)]
                          (print input)
                          (.println System/out input)
                          (flush))
                        (recur))))]
    (clojure.contrib.server-socket/create-server 8080 echo)))

(defn main []
  (println "Hello, World!")
  (echo-server))