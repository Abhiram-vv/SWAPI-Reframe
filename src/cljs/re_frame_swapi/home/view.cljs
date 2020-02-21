(ns re-frame-swapi.home.view
  (:require
    [re-frame.core :as rf]))

(defn container []
  (let [planets- (rf/subscribe [:swapi-planets])
        planet-count- (rf/subscribe [:swapi-planet-count])]
    [:div
     [:h3 (str "This is the component:- " (pr-str @planets-))]
     [:h3 @planet-count-]]))