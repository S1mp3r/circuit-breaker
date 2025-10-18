"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Power } from "lucide-react";
import { Alert, AlertDescription } from "@/components/ui/alert";
import { AlertCircle } from "lucide-react";

export function ServiceToggle() {
  const [isServiceOn, setIsServiceOn] = useState(true);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const toggleService = async () => {
    try {
      setLoading(true);
      setError(null);

      const newState = !isServiceOn;

      const response = await fetch(`http://localhost:8082/api/v1/comments`, {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ isOn: newState }),
      });

      if (!response.ok) {
        throw new Error("Failed to toggle service");
      }

      setIsServiceOn(newState);
    } catch (err) {
      setError(err instanceof Error ? err.message : "An error occurred");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="space-y-2">
      <Button
        onClick={toggleService}
        disabled={loading}
        variant={isServiceOn ? "default" : "destructive"}
        className="gap-2"
      >
        <Power className="h-4 w-4" />
        {loading
          ? "Toggling..."
          : isServiceOn
          ? "Turn Off Comment Service"
          : "Turn On Comment Service"}
      </Button>
      {error && (
        <Alert variant="destructive">
          <AlertCircle className="h-4 w-4" />
          <AlertDescription>{error}</AlertDescription>
        </Alert>
      )}
    </div>
  );
}
