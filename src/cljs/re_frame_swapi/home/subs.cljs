(ns re-frame-swapi.home.subs
  (:require
    [re-frame.core :as rf]))

(defn swapi-planets [db]
  (or (get-in db [:swapi :planets])
      (do
        (rf/dispatch [:swapi-planets/get "https://swapi.co/api/planets/?page=1"])
        :loading)))

(rf/reg-sub :swapi-planets swapi-planets)

(rf/reg-sub
  :swapi-planet-count
  (fn [db]
    (get-in db [:swapi :count])))

(rf/reg-sub
  :swapi-planet-next
  (fn [db]
    (get-in db [:swapi :next])))

(rf/reg-sub
  :swapi-planet-previous
  (fn [db]
    (get-in db [:swapi :previous])))