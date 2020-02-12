(ns re-frame-swapi.events
  (:require
   [re-frame.core :as rf]
   [re-frame-swapi.db :as db]
   ))

(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(rf/reg-event-db
  :routes/set
  (fn [db [_ r]]
    (println r)
    (assoc-in db [:routes :current] r)))