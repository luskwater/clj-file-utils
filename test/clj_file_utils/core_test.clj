
(ns clj-file-utils.core-test
  (:use clj-unit.core clj-file-utils.core :reload)
  (:import java.io.File))

(def foo-name      "test/clj_file_utils/assets/foo.txt")
(def fake-foo-name "test/clj_file_utils/assets/fake-foo.txt")
(def foo      (File. foo-name))

(deftest "file"
  (assert= foo (file foo-name))
  (assert= foo (file (file "test/clj_file_utils/assets") "foo.txt"))
  (assert= foo (file "test" "clj_file_utils" "assets" "foo.txt")))

(deftest "mv-exists"
  (assert-not (exists? fake-foo-name))
  (assert-not (exists? (file fake-foo-name)))
  (do (mv foo-name fake-foo-name) true)
  (assert (exists? fake-foo-name))
  (assert-not (exists? (file foo-name)))
  (do (mv fake-foo-name foo-name) true)
  (assert (exists? foo-name))
  )

(run-tests 'clj-file-utils.core-test)
