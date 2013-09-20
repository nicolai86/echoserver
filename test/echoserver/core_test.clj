(ns echoserver.core-test
  (:require [clojure.test :refer :all]
            [echoserver.core :refer :all]))

(deftest generated-output-test
  (testing "response output"
    (is (= "> foo\n" (generate-output "foo")))))

(deftest generated-input-display
  (testing "request display"
    (is (= "< bar\n" (generate-input-display "bar")))))
