(ns re-frame-swapi.views
  (:require
    [re-frame.core :as rf]
    [re-frame-swapi.subs :as subs]
    [reagent.core :as r]
    [re-frame-swapi.exercise.view :as exercise]
    ))

(defn home []
  [:div
    [:a {:href "#/" :style {:margin "10px"}} "Home"]
    [:a {:href "#/exercise"} "Exercise"]])

(defmulti current-page identity)

(defmethod current-page :home []
  [home])
(defmethod current-page :exercise []
  [exercise/main-panel])
(defmethod current-page :default []
  [:div])
