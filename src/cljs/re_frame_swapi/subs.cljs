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