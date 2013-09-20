(ns echoserver.core
  (:require clojure.contrib.server-socket))

(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])

(defn- echo [in out]
  (let [reader (BufferedReader. (InputStreamReader. in))
          writer (OutputStreamWriter. out)]
      (loop []
        (let [input (. reader readLine)]
          (. writer write (str "> " input))
          (. writer write "\n")
          (. writer flush)
          (println (str "< " input )))
        (recur))))

(defn main []
  (println "Hello, World!")
  (clojure.contrib.server-socket/create-server 8080 echo))