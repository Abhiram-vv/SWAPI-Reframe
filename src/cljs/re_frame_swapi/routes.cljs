(ns re-frame-swapi.routes
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent.core :as reagent]
            [re-frame.core :as rf]
            [secretary.core :as secretary :refer-macros [defroute]])
  (:import goog.history.Html5History))

(defn hook-browser-navigation! []
  (doto (Html5History.)
    (events/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/exercise" []
            (rf/dispatch [:routes/set :exercise]))

  (defroute "/" []
            (rf/dispatch [:routes/set :home]))

  (hook-browser-navigation!))