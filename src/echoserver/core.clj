(ns echoserver.core
  (:require clojure.contrib.server-socket))

(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])

(defn- echo [in out]
  (let [reader (BufferedReader. (InputStreamReader. in))
          writer (OutputStreamWriter. out)]
      (loop []
        (let [input (.readLine reader)]
          (.write writer input)
          (.write writer "\n")
          (.flush writer)
          (println input))
        (recur))))

(defn echo-server []
  (clojure.contrib.server-socket/create-server 8080 echo))

(defn main []
  (println "Hello, World!")
  (echo-server))