(ns re-frame-swapi.routes
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [re-frame.core :as rf]
            [secretary.core :as secretary :refer-macros [defroute]]
            [re-frame-swapi.home.view :as home]
            [re-frame-swapi.exercise.view :as exercise])
  (:import goog.history.Html5History))

(defn hook-browser-navigation! []
  (doto (Html5History.)
    (events/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defmulti current-page identity)

(defmethod current-page :home []
  [home/container])

(defmethod current-page :exercise []
  [exercise/main-panel])

(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/exercise" []
            (rf/dispatch [:routes/set :exercise]))

  (defroute "/" []
            (rf/dispatch [:routes/set :home]))

  (hook-browser-navigation!))