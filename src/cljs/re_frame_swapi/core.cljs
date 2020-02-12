(ns re-frame-swapi.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as rf]
   [re-frame-swapi.events :as events]
   [re-frame-swapi.views :as views]
   [re-frame-swapi.config :as config]
   [re-frame-swapi.routes :as routes]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn render []
  (let [cur-route- (rf/subscribe [:current-route])]
    [views/current-page @cur-route-]))

(defn mount-root []
  (rf/clear-subscription-cache!)
  (routes/app-routes)
  (reagent/render [render]
                  (.getElementById js/document "app")))

(defn init []
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
