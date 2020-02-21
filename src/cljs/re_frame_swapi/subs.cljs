(ns re-frame-swapi.subs
  (:require
    [re-frame.core :as rf]))

(rf/reg-sub
  ::name
  (fn [db]
    (:name db)))


(rf/reg-sub
  :current-route
  (fn [db]
    (get-in db [:routes :current] :home)))

(defn swapi-planets [db]
  (or (get-in db [:swapi :planets])
      (do
        (rf/dispatch [:swapi-planets/get])
        :loading)))

(rf/reg-sub :swapi-planets swapi-planets)

(defn swapi-planets-loading? [db]
  (= (swapi-planets db) :loading))

(rf/reg-sub
  :swapi-planet-count
  (fn [db]
    (when-not (swapi-planets-loading? db)
      (:count (swapi-planets db)))))