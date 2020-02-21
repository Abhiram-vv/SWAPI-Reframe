(ns re-frame-swapi.views
  (:require
    [re-frame-swapi.home.view :as home]
    [re-frame-swapi.exercise.view :as exercise]
    ))

(defmulti current-page identity)

(defmethod current-page :home []
  [home/container])
(defmethod current-page :exercise []
  [exercise/main-panel])
