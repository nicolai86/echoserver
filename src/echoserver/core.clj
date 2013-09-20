(ns echoserver.core
  (:require clojure.contrib.server-socket))

(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])

(defn generate-output [input]
  (str "> " input "\n"))

(defn generate-input-display [input]
  (str "< " input "\n"))

(defn echo [reader writer]
  (let [input (. reader readLine)]
    (. writer write (generate-output input))
    (. writer flush)
    (print (generate-input-display input))
    (flush)))

(defn setup-echo [in out]
  (let [reader (BufferedReader. (InputStreamReader. in))
        writer (OutputStreamWriter. out)]
    (loop []
      (echo reader writer)
      (recur))))

(defn main []
  (println "Hello, World!")
  (clojure.contrib.server-socket/create-server 8080 setup-echo))