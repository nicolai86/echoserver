(ns echoserver.core
  (:require clojure.contrib.server-socket))

(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])

(defn echo-server []
  (letfn [(echo [in out]
    (let [inp (BufferedReader. (InputStreamReader. in))
          outp (OutputStreamWriter. out)]
      (loop []
        (let [input (.readLine inp)]
          (.write outp input)
          (.write outp "\n")
          (.flush outp)
          (println input))
        (recur))))]
  (clojure.contrib.server-socket/create-server 8080 echo)))

(defn main []
  (println "Hello, World!")
  (echo-server))