package listeners;
/**
 * The class describes a hit notifier.
 * @author Daniel Hermon.
 */
public interface HitNotifier {
    /**
     * The method adds the listener from the hit notifier.
     * @param hl -The listener that we should add.
     */
       void addHitListener(HitListener hl);
       /**
         * The method removes the listener from the hit notifier.
         * @param hl -The listener that we should remove.
         */
      void removeHitListener(HitListener hl);
}
