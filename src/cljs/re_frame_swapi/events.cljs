(ns re-frame-swapi.events
  (:require
    [re-frame.core :as rf]
    [re-frame-swapi.db :as db]
    [ajax.core :as ajax]
    [camel-snake-kebab.core :refer [->kebab-case-keyword]]
    [camel-snake-kebab.extras :refer [transform-keys]]))

(rf/reg-event-db
  ::initialize-db
  (fn [_ _]
    db/default-db))

(rf/reg-event-db
  :routes/set
  (fn [db [_ r]]
    (assoc-in db [:routes :current] r)))

(rf/reg-event-db
  :success-get-result
  (fn [db [_ result]]
    (assoc-in db [:swapi :planets] (transform-keys ->kebab-case-keyword result))))

(rf/reg-event-db
  :failure-get-result
  (fn [db [_ result]]
    (assoc-in db [:swapi :planets] result)))


; db vs fx
;(rf/reg-event-db
;  :something
;  (fn [db]
;    (assoc-in db [:some :thing] "something")))
;
;(rf/reg-event-fx
;  :something
;  (fn [{:keys [db local-storage]}]
;    {:db            (assoc-in db [:some :thing] "something")
;     :local-storage (assoc-in local-storage [:local] "thing")}))

(rf/reg-event-db
  :swapi-planets/get
  (fn [db [_]]
    (ajax/GET "https://swapi.co/api/planets" {:handler       #(rf/dispatch [:success-get-result %])
                                              :error-handler #(rf/dispatch [:failure-get-result %])})
    (assoc-in db [:swapi :planets] :loading)))